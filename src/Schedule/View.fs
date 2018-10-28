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

let getSchedule = 
  promise {
      let! res = fetch "https://realbrokenprogrammer.github.io/api/schedule.json" []
      let! json = res.text()
      let t = ofJson<Schedule> json
      console.log(t)
      return t
    } |> Promise.start

let event dateTime title description url = 
  div
    [ ClassName "event" ]
    [ h1
        [  ]
        [ str title ] ]

let tt title = 
  let d = document.createElement("p")
  d.textContent <- title
  d

let renderSchedule (schedule : Schedule) = 
  let scheduleContainer = document.querySelector("#schedule-container")
  
  for e in schedule.events do
    scheduleContainer.appendChild(tt e.title) |> ignore

let root = 
  let t = div [Id "schedule-container"] []
  

  fetch "https://realbrokenprogrammer.github.io/api/schedule.json" []
  |> Promise.bind(fun res -> res.text())
  |> Promise.map(fun txt -> ofJson<Schedule> txt)
  |> Promise.map(renderSchedule)
  |> Promise.start

  t
