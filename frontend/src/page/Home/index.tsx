import { Card, Container } from "react-bootstrap";
import "./index.css";
import { NavbarLandingPage } from "../../components/NavbarLandingPage";
import { Footer } from "../../components/Footer/index,";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { getSession } from "../../service/auth";

export function Home() {
  const navigate = useNavigate();

  useEffect(() => {
    getSession().then((value) => {
      if (value !== null) {
        navigate("/project/list", {
          replace: true,
        });
      }
    });
  }, []);

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
