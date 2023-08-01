import { Button, Card, Container } from "react-bootstrap";
import "./index.css";
import { Link } from "react-router-dom";

export function NoAuthentication() {
  return (
    <>
      <div className="ajust">
        <Container>
          <Card>
            <Card.Header className="center">NoAuthentication</Card.Header>
            <Card.Body className="center">go back to login</Card.Body>
            <Card.Footer>
              <div className="d-grid gap-2">
                <Button variant="primary" size="lg" as={Link} to={"/"}>
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
