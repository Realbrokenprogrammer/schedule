module Schedule.State

open Types

let init() : Model = 
  Schedule.Default()

let update (msg:Msg) (model:Model) = 
  match msg with
  | Update s -> 
    s
