import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import { signOut } from "../../service/auth";
import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { User } from "@supabase/supabase-js";

export function NavBar() {
  const navigate = useNavigate();
  const [user, setUser] = useState<User>();

  useEffect(() => {
    checkUser();
  }, [user]);

  function checkUser() {
    
  }

  function handleSignout() {
    signOut().then(() => {
      navigate("/", {
        replace: true,
      });
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
                to={"/project/save"}
              >
                Save
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item
                as={Link}
                to={"/project/list"}
              >
                List
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
          <Nav>
            <Nav.Link onClick={handleSignout}>Signout</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}