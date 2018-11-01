module Schedule.Types

open Fable.Import.Browser
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
            let secondsDifference = abs (x.time.TimeOfDay.TotalSeconds - DateTime.Now.TimeOfDay.TotalSeconds)
            console.log(secondsDifference)
            if (0.0 <= secondsDifference && secondsDifference < 4.0 * 60.0 * 60.0) then
                Current
            else
                Past
        | (eventDay, day) when eventDay > day -> Future
        | _ -> Future
    member x.GetDateTime() =
        let eventDay = x.days.[0]
        let eventTimeFrame = x.ToEventTime()
        
        let addTime (date : DateTime) = 
          date.Add(x.time.TimeOfDay)
        match eventTimeFrame with
        | Past    -> DateTime.Today.Subtract(TimeSpan.FromDays(float (int DateTime.Today.DayOfWeek - eventDay)))
        | Current -> DateTime.Today
        | Future  -> DateTime.Today.AddDays(float (eventDay - int DateTime.Today.DayOfWeek))
        |> addTime
    member x.GetCountdown() =
        let eventDay = x.days.[0]
        let eventTimeFrame = x.ToEventTime()
        let eventTime = x.GetDateTime()

        match eventTimeFrame with
        | Past    -> sprintf "Finished %d hours ago." (DateTime.Now.Subtract(eventTime.TimeOfDay).TimeOfDay.Hours)
        | Current ->
            let timeDifference = (DateTime.Now.TimeOfDay.Subtract(eventTime.TimeOfDay))
            match timeDifference with
            | timeDifference when timeDifference.Hours = 0 -> 
                sprintf "Started %d minutes ago."    (timeDifference.Minutes)
            | _ -> 
                sprintf "Started %d hours ago."    (timeDifference.Hours)
        | Future  -> sprintf "Starts in %d days."     (eventDay - int DateTime.Today.DayOfWeek)

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