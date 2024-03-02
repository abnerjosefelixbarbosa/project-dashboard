import { Button, Card, Container } from "react-bootstrap";
import "./index.css";
import { useNavigate } from "react-router-dom";

export function NoAuthentication() {
  const navigate = useNavigate();

  function handleBackLogin() {
    navigate("/account/login", {
      replace: true,
    });
  }

  return (
    <>
      <div className="ajust">
        <Container>
          <Card>
            <Card.Header className="center">NoAuthentication</Card.Header>
            <Card.Body className="center">go back to login</Card.Body>
            <Card.Footer>
              <div className="d-grid gap-2">
                <Button variant="primary" size="lg" onClick={handleBackLogin}>
                  Back Login
                </Button>
              </div>
            </Card.Footer>
          </Card>
        </Container>
      </div>
    </>
  );
}
