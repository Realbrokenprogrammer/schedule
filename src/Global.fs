module Global

type Page =
  | Home
  | Schedule
  | About

let toHash page =
  match page with
  | About -> "#about"
  | Schedule -> "#schedule"
  | Home -> "#home"
