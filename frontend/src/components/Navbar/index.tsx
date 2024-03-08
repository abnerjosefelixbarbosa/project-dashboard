import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import { Link, useNavigate, useSearchParams } from "react-router-dom";

export function NavBar() {
  const navigate = useNavigate();
  const [params] = useSearchParams();
  const token = params.get('token');
  const accountId = params.get('accountId');

  function handleSignOut() {
    navigate("/account/login", {
      replace: true,
    });
  }

  return (
    <Navbar
      bg="dark"
      data-bs-theme="dark"
      expand="lg"
      className="bg-body-tertiary"
      sticky="top"
    >
      <Container>
        <Navbar.Brand>Dashboard</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <NavDropdown title="Project" id="basic-nav-dropdown">
              <NavDropdown.Item
                as={Link}
                to={`/project/create?token=${token}&accountId=${accountId}`}
              >
                Create
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item
                as={Link}
                to={`/project/list?token=${token}&accountId=${accountId}`}
              >
                List
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
          <Nav>
            <Nav.Link onClick={handleSignOut}>SignOut</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}