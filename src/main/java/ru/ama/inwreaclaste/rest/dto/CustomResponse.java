package ru.ama.inwreaclaste.rest.dto;

import ru.ama.inwreaclaste.error.Error;

public record CustomResponse<T>( boolean success, T body, Error error) {}
