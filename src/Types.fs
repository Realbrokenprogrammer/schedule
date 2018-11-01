module App.Types

open Global

type Msg =
  | ScheduleMsg of Schedule.Types.Msg

type Model = {
    currentPage: Page
    schedule: Schedule.Types.Model
  }
