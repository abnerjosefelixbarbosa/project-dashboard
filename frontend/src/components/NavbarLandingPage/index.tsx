import { Container, Nav, Navbar } from "react-bootstrap";
import { Link } from "react-router-dom";

export function NavbarLandingPage() {
  return (
    <Navbar
      bg="dark"
      data-bs-theme="dark"
      expand="lg"
      className="bg-body-tertiary top_nav"
      sticky="top"
    >
      <Container>
        <Navbar.Brand>Dashboard</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link as={Link} to={"/"}>
              Home
            </Nav.Link>
            <Nav.Link as={Link} to={"/about"}>
              About
            </Nav.Link>
          </Nav>
          <Nav>
            <Nav.Link as={Link} to={"/account/login"}>
              SignIn
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}
