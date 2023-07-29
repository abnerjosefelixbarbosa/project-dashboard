import { Container, Row } from "react-bootstrap";
import { FormLogin } from "./components/formLogin";
import "./index.css";

export function Login() {
  return (
    <>
      <div className="ajust">
        <Container className="container_form">
          <Row>
            <FormLogin />
          </Row>
        </Container>
      </div>
    </>
  );
}
