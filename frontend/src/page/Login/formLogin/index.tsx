import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { getSession } from "../../../service/auth";
import { Auth } from "@supabase/auth-ui-react";
import { supabase } from "../../../service/subabase";
import { ThemeSupa } from "@supabase/auth-ui-shared";
import { Container, Row } from "react-bootstrap";

export function FormLogin() {
  const navigate = useNavigate();

  useEffect(() => {
    handleSession()
  }, []);

  function handleSession() {
    getSession().then((data) => {
      if (data !== null) {
        navigate("/project/list", {
          replace: true,
          state: {
            user: data.user
          }
        });
      }
    });
  }

  return (
    <>
      <Container className="container_form">
        <Row>
          <Auth
            supabaseClient={supabase}
            appearance={{ theme: ThemeSupa }}
            providers={["github"]}
          />
        </Row>
      </Container>
    </>
  );
}
