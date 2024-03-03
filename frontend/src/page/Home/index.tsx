import { Card, Container } from "react-bootstrap";
import "./index.css";
import { NavbarLandingPage } from "../../components/NavbarLandingPage";
import { Footer } from "../../components/Footer";

export function Home() {
  return (
    <>
      <NavbarLandingPage />
      <div className="ajust2">
        <Container>
          <Card>
            <Card.Header className="text-center">Project Dashboard</Card.Header>
            <Card.Body>
              <Card.Title className="text-center">Welcome</Card.Title>
              <br />
              <div className="text-center">To start a project, do sign in</div>
            </Card.Body>
          </Card>
        </Container>
      </div>
      <Footer />
    </>
  );
}
