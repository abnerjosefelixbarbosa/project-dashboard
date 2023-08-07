import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { authentication as serviceGetSession } from "../../../service/auth";
import { Auth } from "@supabase/auth-ui-react";
import { supabase } from "../../../service/subabase";
import { ThemeSupa } from "@supabase/auth-ui-shared";
import { Container, Row } from "react-bootstrap";

export function FormLogin() {
  const navigate = useNavigate();

  useEffect(() => {
    serviceGetSession().then((value) => {
      if (value) {
        navigate("/project/list", {
          replace: true,
          state: {
            user: value
          }
        });
      }
    });
  });

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
