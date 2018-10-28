module Schedule.Types

open System

type Event = {
        dateTime    : DateTime
        title       : string
        description : string
        url         : string
    }