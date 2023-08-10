import { Auth } from "@supabase/auth-ui-react";
import { supabase } from "../../../service/subabase";
import { ThemeSupa } from "@supabase/auth-ui-shared";
import { Container, Row } from "react-bootstrap";

export function FormLogin() {
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
