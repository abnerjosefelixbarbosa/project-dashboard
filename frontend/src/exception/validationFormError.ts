export class ValidationFormError extends Error {
    constructor(message: string) {
        super(message);
    }
}