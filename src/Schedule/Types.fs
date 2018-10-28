module Schedule.Types

open System

type Event = {
        time        : DateTime
        title       : string
        description : string
        url         : string
        days        : int[]
    }

type Schedule = {
        events   : Event[]
        timezone : string
    } with
    static member Default() = {events = Array.empty; timezone = ""}

type Model = Schedule

type Msg = 
    | Update of Schedule