module Schedule.View

open Fable.Helpers.React
open Fable.Helpers.React.Props
open Fable.PowerPack
open Fable.PowerPack.Fetch
open Fable.PowerPack.Result
open Fable.Core.JsInterop
open Fable.Import.Browser
open Types
open Fable.Core
open Elmish.React.Components

let getSchedule dispatch = 
  promise {
      let! res = fetch "https://realbrokenprogrammer.github.io/api/schedule.json" []
      let! json = res.text()
      let t = ofJson<Schedule> json
      Update t |> dispatch
    } |> Promise.start

let event e = 
  div
    [ ClassName "event" ]
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
    [for e in model.events do
      yield event e]