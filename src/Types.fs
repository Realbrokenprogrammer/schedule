module App.Types

open Global

type Msg =
  | ScheduleMsg of Schedule.Types.Msg
  | HomeMsg of Home.Types.Msg

type Model = {
    currentPage: Page
    home: Home.Types.Model
    schedule: Schedule.Types.Model
  }
