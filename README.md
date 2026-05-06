# iFlag Gym Management System

iFlag is a Java Spring Boot web application for managing gym members, coaches, attendance records, member session balances, and monthly clash results.

The system supports four roles:

- Owner
- Admin
- Coach
- Member

Version 1 focuses on the core gym-management workflow, including user management, member and coach profiles, attendance tracking, clash results, member self-service pages, active/inactive user handling, manual password reset, validation, backend search, and a clean dark-themed UI.

---

## Tech Stack

- Java
- Spring Boot
- Spring MVC
- Spring Security
- Thymeleaf
- MySQL
- JPA/Hibernate
- Maven
- HTML/CSS

---

## Main Features

- Role-based authentication and authorization
- Owner, Admin, Coach, and Member dashboards
- Admin user management
- Activate/deactivate user accounts
- Manual password reset by admin/owner
- Member profile management
- Coach profile management
- Attendance CRUD
- Member session tracking
- Member remaining-session calculation
- Clash event creation
- Clash result CRUD
- Duplicate clash-result prevention
- Member-side attendance history
- Member-side clash results
- Backend search by member name or member profile ID
- Active-only member/coach filtering for new records
- Validation and error handling
- Clean dark iFlag-style UI

---

## Roles and Access

### Owner

The owner can:

- View system-wide dashboard counts
- Access admin pages
- View all users
- View member profiles
- View coach profiles
- View attendance records
- View clash results

### Admin

The admin can:

- Manage users
- Create member profiles
- Create coach profiles
- Manage attendance records
- Manage clash events
- Manage clash results
- Activate/deactivate users
- Reset user passwords manually

### Member

The member can:

- View their own dashboard
- View their own attendance history
- View their own clash results
- View their total, extra, used, and remaining sessions

Members cannot access admin or owner pages.

### Coach

The coach has a basic Version 1 dashboard.

Full coach-side features and scheduling are planned for Version 2.

---

## Business Rules

- One user can have only one member profile.
- One user can have only one coach profile.
- One member can have only one clash result per clash event.
- Only ATTENDED sessions reduce remaining sessions.
- MISSED and CANCELLED sessions do not reduce remaining sessions.
- Inactive users cannot log in.
- Inactive users remain visible in history.
- Inactive users cannot create new member or coach profiles.
- Inactive members are hidden from new attendance and clash-result forms.
- Inactive coaches are hidden from new attendance forms.
- Old attendance and clash records remain visible even if a user later becomes inactive.

---

## Version 1 Status

Version 1 is complete and demo-ready.

Completed areas:

- Authentication
- Role-based security
- User management
- Member profiles
- Coach profiles
- Attendance management
- Clash event/result management
- Member dashboard
- Owner dashboard
- Coach dashboard placeholder
- Validation and error handling
- UI cleanup
- Backend member search
- Active/inactive user lifecycle
- Manual password reset
- Final testing
- Demo data preparation

---

## Data Initialization

The project originally used a `DataInitializer` to create starter demo users.

For a more realistic setup, the initializer is now controlled using the `dev` profile.

This means:

- `DataInitializer` only runs when the `dev` profile is active.
- It does not run during normal presentation/production-style use.
- Existing MySQL data stays in the database even when the initializer is disabled.

This avoids recreating demo users every time the application starts.

---

## Password Reset Approach

Version 1 includes manual password reset by admin/owner.

The admin or owner can:

- Open the All Users page
- Click Reset Password
- Enter a new password
- Confirm the password
- Save the reset password

The new password is encoded before being saved.

Email-based forgot-password is planned for Version 2.

---

## Current Limitations

- Coach-side features are basic in Version 1.
- Schedule management is postponed to Version 2.
- Forgot-password is manual by admin/owner, not email-based.
- Advanced autocomplete search is not implemented yet.
- Reports and analytics are not implemented yet.
- Some production hardening items are planned for Version 2.

---

## Version 2 Roadmap

Planned improvements for Version 2:

- Full coach-side dashboard
- Schedule module
- Coach schedule management
- Email-based forgot-password flow
- Advanced autocomplete search
- Reports and analytics
- Better production-level permission rules
- Prevent admin from resetting owner password
- Convert remaining data-changing links, such as delete actions, to POST forms
- Deployment/live hosting setup
- Database backup strategy
- Audit logging for sensitive actions such as password reset, activate/deactivate, and delete
- More advanced UI improvements

---

## Demo Notes

Recommended demo flow:

1. Login as Owner
2. Show Owner Dashboard
3. Open All Users
4. Show activate/deactivate and reset password
5. Login as Admin
6. Add attendance using member search by name or ID
7. Add clash result using member search by name or ID
8. Login as Member
9. Show Member Dashboard
10. Show My Attendance
11. Show My Clash Results
12. Login as Coach
13. Explain coach-side features are planned for Version 2

---

## Final Project Condition

iFlag Version 1 is complete, tested, and ready for presentation.

The project demonstrates real backend development concepts such as:

- Authentication
- Authorization
- Password hashing
- Role-based access control
- MVC structure
- JPA/Hibernate relationships
- MySQL persistence
- Form validation
- Business rules
- User lifecycle management
- Search/filtering
- Clean UI styling