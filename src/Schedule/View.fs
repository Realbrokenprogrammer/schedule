module Schedule.View

open Fable.Helpers.React
open Fable.Helpers.React.Props
open Fable.PowerPack
open Fable.PowerPack.Fetch
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

//TODO: Sort days and order them depending on day in the week.
//  Day 1: Monday, Day 7: Sunday
let event e = 
  let time = EventTime.fromEvent e ((int) DateTime.Today.DayOfWeek)
  
  div
    [ classList [ ("event", true); ("past", time = Past) ] ]
    [ h1
        [ ClassName "event-title" ]
        [ a 
            [ Href e.url ] 
            [ str e.title ]] 
      div
        [ ClassName "event-time" ]
        //TODO: Fix date and time representation.
        [ p [] [ str (e.time.ToString()) ]]
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