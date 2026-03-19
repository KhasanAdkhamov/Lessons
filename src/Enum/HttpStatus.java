package Enum;

public enum HttpStatus {
    OK(200, "Ok"),
    CREATED(201, "Created"),
    BAD_REQUEST(400, "Bd_r"),
    UNAUTHORIZED(401, "Unautor"),
    NOT_FOUND(404, "Not_found"),
    INTERNAL_ERROR(500, "Int_error");

    private final int code;
    private final String description;

    HttpStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public boolean isSuccess() {
        return this.code >= 200 && this.code < 300;
    }

    public boolean isError() {
        return this.code >= 400;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
