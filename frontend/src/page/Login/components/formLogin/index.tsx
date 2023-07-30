import { supabase } from "../../../../service/subabase";
import { Form } from "react-bootstrap";

export function FormLogin() {

  supabase.auth.signInWithOAuth({provider: "github"}).then(() => {
  })

  return (
    <>
       <Form>
        <Form.Group className="mb-3">
          <Form.Label>User:</Form.Label>
          <Form.Control type="text" />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Password:</Form.Label>
          <Form.Control type="password" />
        </Form.Group>
      </Form>
    </>
  );
}

/*
<Auth
        supabaseClient={supabase}
        appearance={{ theme: ThemeSupa }}
        providers={["github"]}
      />
*/