package ru.ama.inwreaclaste.error;

import org.springframework.http.HttpStatusCode;

public record Error( HttpStatusCode httpStatusCode, String code, String title, Long time ) {}
