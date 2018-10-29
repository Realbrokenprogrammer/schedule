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

let dateFormat date =
  Format.localFormat Local.english date "MMM dd, yyyy hh:mm tt"


let event (e : Event) = 
  let eventDay = e.days.[0]
  let eventTimeFrame = e.ToEventTime()

  let eventTime = 
    let addTime (date : DateTime) = 
      date.Add(e.time.TimeOfDay)
    match eventTimeFrame with
    | Past    -> DateTime.Today.Subtract(TimeSpan.FromDays(float (int DateTime.Today.DayOfWeek - eventDay)))
    | Current -> DateTime.Today
    | Future  -> DateTime.Today.AddDays(float (eventDay - int DateTime.Today.DayOfWeek))
    |> addTime

  let countDown = 
    match eventTimeFrame with
    | Past    -> sprintf "Finished %d hours ago." (DateTime.Now.Subtract(eventTime.TimeOfDay).TimeOfDay.Hours)
    | Current -> sprintf "Starts in %d hours."    (eventTime.Subtract(DateTime.Now.TimeOfDay).Hour)
    | Future  -> sprintf "Starts in %d days."     (eventDay - int DateTime.Today.DayOfWeek)

  div
    [ classList [ ("event", true); ("past", eventTimeFrame = Past); ("current", eventTimeFrame = Current) ] ]
    [ h1
        [ ClassName "event-title" ]
        [ a 
            [ Href e.url ] 
            [ str e.title ]] 
      div
        [ ClassName "event-time" ]
        [ p [] [ str (eventTime |> dateFormat) ]
          p [] [ str (countDown) ]]
      div
        [ ClassName "event-description" ]
        [ p [] [ str e.description ]]]

let root model dispatch = 
  //TODO: getSchedule should only be called once on startup.
  if model.events.Length = 0 then
    getSchedule dispatch

  div 
    [ClassName "schedule-container"]
    //TODO: Display a couple of more events than 7. (Past and future)
    [for e in model.SortEvents() do
      yield event e]