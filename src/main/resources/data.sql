INSERT INTO base_user (id, login, password, email, role, registration_time) VALUES ('1', 'debt', '$2a$10$IJqXG1WQH9hrg90qs.uSd.rY8tWwg09VmOZDzpwdiuabXxWffedsa',' debt@sobaka.com', 0, '1990-01-01 12:00:00.000000+00') ON CONFLICT DO NOTHING;