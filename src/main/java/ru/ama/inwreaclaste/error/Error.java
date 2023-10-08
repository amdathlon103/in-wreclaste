package ru.ama.inwreaclaste.error;

import org.springframework.http.HttpStatusCode;

import java.time.ZonedDateTime;

public record Error( HttpStatusCode httpStatusCode, String code, String title, ZonedDateTime text ) {}
