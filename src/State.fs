module App.State

open Elmish
open Elmish.Browser.Navigation
open Elmish.Browser.UrlParser
open Fable.Import.Browser
open Global
open Types

let pageParser: Parser<Page->Page,Page> =
  oneOf [
    map Schedule (s "schedule")
  ]

let urlUpdate (result: Option<Page>) model =
  match result with
  | None ->
    console.error("Error parsing url")
    model,Navigation.modifyUrl (toHash model.currentPage)
  | Some page ->
      { model with currentPage = page }, []

let init result =
  let schedule = Schedule.State.init()
  let (model, cmd) =
    urlUpdate result
      { currentPage = Schedule
        schedule = schedule}
  model, Cmd.batch [ Cmd.none ]

let update msg model =
  match msg with
  | ScheduleMsg msg -> 
    let schedule = Schedule.State.update msg model.schedule
    { model with schedule = schedule}, Cmd.none
