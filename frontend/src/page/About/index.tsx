import { Card, Container } from "react-bootstrap";
import { NavbarLandingPage } from "../../components/NavbarLandingPage";
import { Footer } from "../../components/Footer/index,";

export function About() {
  return (
    <>
      <NavbarLandingPage />
      <div className="ajust2">
        <Container>
          <Card>
            <Card.Header className="text-center">Project Dashboard</Card.Header>
            <Card.Body>
              <Card.Title className="text-center">About</Card.Title>
              <Card.Title className="text-center">
                check the deadlines of your projects
                to have full control of activities.
              </Card.Title>
            </Card.Body>
          </Card>
        </Container>
      </div>
      <Footer />
    </>
  );
}
