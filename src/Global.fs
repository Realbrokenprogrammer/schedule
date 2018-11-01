module Global

type Page =
  | Schedule

let toHash page =
  match page with
  | Schedule -> "#schedule"
