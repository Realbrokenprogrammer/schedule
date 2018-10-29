module Schedule.View

open Fable.Helpers.React
open Fable.Helpers.React.Props
open Fable.PowerPack
open Fable.PowerPack.Fetch
open Fable.PowerPack.Date
open Fable.Core.JsInterop
open Fable.Import.Browser
open Types
open System

let getSchedule dispatch = 
  promise {
      let! res = fetch "https://realbrokenprogrammer.github.io/api/schedule.json" []
      let! json = res.text()
      let t = ofJson<Schedule> json
      Update t |> dispatch
    } |> Promise.start

let event (e : Event) = 
  let time = e.ToEventTime()
  
  let dateTime = 
    let dateFormat date = 
      Format.localFormat Local.english date "MMM dd, yyyy"
    match time with
    | Past -> DateTime.Today.Subtract(TimeSpan.FromDays(float (int DateTime.Today.DayOfWeek - e.days.[0])))
    | Current -> DateTime.Today
    | Future -> DateTime.Today.AddDays(float (e.days.[0] - int DateTime.Today.DayOfWeek))
    |> dateFormat

  div
    [ classList [ ("event", true); ("past", time = Past); ("current", time = Current) ] ]
    [ h1
        [ ClassName "event-title" ]
        [ a 
            [ Href e.url ] 
            [ str e.title ]] 
      div
        [ ClassName "event-time" ]
        //TODO: Fix date and time representation.
                // Mon, Oct 29, 2018 14:00 (Countdown (Starts in... 5 hours))
        [ p [] [ str (dateTime) ]]
      div
        [ ClassName "event-description" ]
        [ p [] [ str e.description ]]]

let root model dispatch = 
  //TODO: getSchedule should only be called once on startup.
  if model.events.Length = 0 then
    getSchedule dispatch

  div 
    [ClassName "schedule-container"]
    //TODO: Organize event ordering depending on days.
    //TODO: Display a couple of more events than 7. (Past and future)
    [for e in model.SortEvents() do
      yield event e]