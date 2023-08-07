import { Button, Card, Container } from "react-bootstrap";
import "./index.css";
import { Link } from "react-router-dom";

export function Home() {
  return (
    <>
      <div>
        <Container className="container_card_info">
          <Card>
            <Card.Header className="text-center">Project Dashboard</Card.Header>
            <Card.Body>
              <Card.Title className="text-center">Welcome</Card.Title>
              <Card.Text className="text-center">
                Click to start a session
              </Card.Text>
            </Card.Body>
            <Card.Footer className="text-center">
              <Button className="btn_session" as={Link} to={"/login"}>
                Start Session
              </Button>
            </Card.Footer>
          </Card>
        </Container>
      </div>
      <div className="part_2"></div>
    </>
  );
}
