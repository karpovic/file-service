package lt.karpovic.fileservice.common;

public enum ResponseStatusEnum {
    /**
     * Common status codes:
     * 200	Indicates that the request was completed successfully.
     * 201	Indicates that a record was created successfully.
     * 204	Indicates that a record was deleted successfully.
     * 40X (401, 404)	Status codes in the 400 range indicate a client error, such as 400 for invalid request syntax.
     * 50X (500, 503)	Status codes in the 500 range indicate that a server error occurred. The client request may have been valid or invalid, but a problem occurred on the server that prevented it from processing the request.
     */
    COMPLETED_SUCCESS(200),
    CREATED_SUCCESS(201),
    DELETED_SUCCESS(204),
    CLIENT_ERROR(400),
    SERVER_ERROR(500);

    private int statusCode;

    ResponseStatusEnum(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
