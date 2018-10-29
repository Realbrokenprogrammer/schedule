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
        | Current -> sprintf "Starts in %d hours."    (eventTime.Subtract(DateTime.Now.TimeOfDay).Hour)
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