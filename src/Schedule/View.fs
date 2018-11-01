module Schedule.View

open Fable.Helpers.React
open Fable.Helpers.React.Props
open Fable.PowerPack
open Fable.PowerPack.Fetch
open Fable.PowerPack.Date
open Fable.Core.JsInterop
open Types

let getSchedule dispatch = 
  promise {
      let! res = fetch "https://realbrokenprogrammer.github.io/api/schedule.json" []
      let! json = res.text()
      let t = ofJson<Schedule> json
      Update t |> dispatch
    } |> Promise.start

let dateFormat date =
  Format.localFormat Local.english date "MMM dd, yyyy hh:mm tt"


let event (targetEvent : Event) = 
  let eventTimeFrame = targetEvent.ToEventTime()

  div
    [ classList [ ("event", true); ("past", eventTimeFrame = Past); ("current", eventTimeFrame = Current) ] ]
    [ div 
        [ ClassName "event-twitch"]
           [ a [ classList [("not-showing", eventTimeFrame <> Current); ("event-watch", true);]
                 Href "https://www.twitch.tv/realbrokenprogrammer"]
               [ i [classList [("fa", true); ("fa-twitch", true); ("fa-3x", true); ("aria-hidden", true)]]
                   []]]
      h1
        [ ClassName "event-title" ]
        [ a 
            [ Href targetEvent.url ] 
            [ str targetEvent.title ]] 
      div
        [ ClassName "event-time" ]
        [ p [] [ str (targetEvent.GetDateTime() |> dateFormat) ]
          p [] [ str (targetEvent.GetCountdown()) ]]
      div
        [ ClassName "event-description" ]
        [ p [] [ str targetEvent.description ]]]

let root model dispatch = 
  //TODO: getSchedule should only be called once on startup.
  if model.events.Length = 0 then
    getSchedule dispatch

  div 
    [ClassName "schedule-container"]
    //TODO: Display a couple of more events than 7. (Past and future)
    [for e in model.SortEvents() do
      yield event e]