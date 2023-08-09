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
              <Card.Title className="text-center">Welcome</Card.Title>
              <Card.Title className="text-center">
                Start a project by doing sign In
              </Card.Title>
            </Card.Body>
          </Card>
        </Container>
      </div>
      <Footer />
    </>
  );
}
