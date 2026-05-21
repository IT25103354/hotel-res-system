# Frontend to Backend Connection

## MVC flow

```
HTML form  -->  Controller  -->  Service  -->  FileHandler  -->  data/*.txt
```

## Example: Register

1. User opens `login.html` or `register.html` (static files).
2. `register.html` form posts to `POST /register`.
3. `UserController.registerUser()` builds a `User` and calls `UserService.registerUser()`.
4. `UserService` reads/writes `data/users.txt` via `FileHandler`.
5. Controller redirects to `login.html` on success.

## Example: Book a room

1. User logs in via `POST /login` → session stores `User`.
2. `GET /rooms` → `RoomController.viewRooms()` → `RoomService.getRooms()` → `rooms.html`.
3. User clicks Book → `GET /booking?roomId=R1` → `booking.html`.
4. Form posts to `POST /bookings/create` → `BookingController.createBooking()` → `BookingService.createBooking()`.

## Access levels

- `customer`: rooms, booking, own bookings.
- `admin`: `/admin` dashboard; `AdminService.isAdmin(role)` guards admin routes and `POST /rooms/add`.

## Test accounts

- Admin: `admin@hotel.com` / `admin123`
- Register new customers from `register.html`
