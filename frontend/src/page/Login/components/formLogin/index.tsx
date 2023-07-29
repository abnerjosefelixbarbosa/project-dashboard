import { Form } from "react-bootstrap";

export function FormLogin() {
  return (
    <>
      <Form>
        <Form.Group className="mb-3">
          <Form.Label>User:</Form.Label>
          <Form.Control type="text" />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Password:</Form.Label>
          <Form.Control type="password" />
        </Form.Group>
      </Form>
    </>
  );
}
