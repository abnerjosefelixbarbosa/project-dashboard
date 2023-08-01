import { useEffect, useState } from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import { signout as serviceSignout } from "../../service/auth";
import { Link, useNavigate } from "react-router-dom";

interface UserData {
  userName: string;
}

export function NavBar({ userName }: UserData) {
  const [name, setName] = useState<string>("");
  const navigate = useNavigate();

  useEffect(() => {
    getUserData();
  });

  function getUserData() {
    if (userName) {
      const list = userName.split(" ");
      setName(list[0]);
    }
  }

  function signout() {
    serviceSignout().then(() => {
      navigate("/", {
        replace: true,
      });
    });
  }

  return (
    <Navbar expand="lg" className="bg-body-tertiary" sticky="top">
      <Container>
        <Navbar.Brand>Hello {name}</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <NavDropdown title="Project" id="basic-nav-dropdown">
              <NavDropdown.Item as={Link} to={"/project/save"}>
                Save
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item as={Link} to={"/project/list"}>
                List
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
          <Nav>
            <Nav.Link onClick={signout}>Signout</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}
