module Schedule.Types

open System

type EventTime = 
    | Past
    | Current
    | Future

type Event = {
        time        : DateTime
        title       : string
        description : string
        url         : string
        days        : int[]
    } with
    member x.ToEventTime() = 
        let eventDay = x.days.[0]
        let day = int DateTime.Today.DayOfWeek

        match (eventDay, day) with
        | (eventDay, day) when eventDay < day -> Past
        | (eventDay, day) when eventDay = day -> 
            if (DateTime.Now.TimeOfDay > x.time.TimeOfDay) then
                Past
            else
                Current
        | (eventDay, day) when eventDay > day -> Future
        | _ -> Future

type Schedule = {
        events   : Event[]
        timezone : string
    } with
    static member Default() = {events = Array.empty; timezone = ""}
    member x.SortEvents() = 
        let splitDays event = 
            [|for day in event.days do
                yield {event with days = [|day|]}|]

        let singleDayEvents = Array.filter (fun event -> event.days.Length = 1) x.events

        Array.filter (fun event -> event.days.Length > 1) x.events
        |> Array.fold (fun arr element -> Array.append arr (element |> splitDays)) [||]
        |> Array.append singleDayEvents
        |> Array.sortBy (fun event -> event.days, event.time)

type Model = Schedule

type Msg = 
    | Update of Schedule