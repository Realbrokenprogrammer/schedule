module Schedule.View

open Fable.Helpers.React
open Fable.Helpers.React.Props
open Fable.Core.JsInterop
open Types

let event dateTime title description url = 
  div
    [ ClassName "event" ]
    [ h1
        [  ]
        [ str title ] ]

let root = 
  div
    [ ClassName "content" ]
    [ h1
        [ ]
        [ str "Schedule" ]
      event "" "First event" "" ""
      event "" "Second event" "" "" ]
