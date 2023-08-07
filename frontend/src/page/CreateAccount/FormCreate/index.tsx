import { zodResolver } from "@hookform/resolvers/zod";
import { Button, Container, Form, Row } from "react-bootstrap";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { authentication, signUp as serviceSignUp } from "../../../service/auth"
import "react-toastify/ReactToastify.css";
import { ToastContainer, toast } from "react-toastify";
import { useEffect } from "react";

const schema = z.object({
  email: z.string().email({ message: "email invalid" }),
  password: z.string(),
});

type FormCreate = z.infer<typeof schema>;

export function FormCreate() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormCreate>({
    mode: "all",
    reValidateMode: "onSubmit",
    resolver: zodResolver(schema),
  });

  useEffect(() => {
    authentication().then()
  })

  function handleSignUp(data: FormCreate) {
    serviceSignUp({...data, full_name: "Abner José Felix Barbosa"})
    .then(() => toast.success("check your email"))
    .catch((e) => toast.error(e.message));
  }

  return (
    <>
      <ToastContainer />
      <Container className="container_form">
        <Row>
          <Form onSubmit={handleSubmit(handleSignUp)}>
            <Form.Group className="mb-3">
              <Form.Label>Email address</Form.Label>
              <Form.Control
                {...register("email")}
                type="email"
                placeholder="Enter email"
              />
              <Form.Text className="text_color">
                {errors.email?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Password</Form.Label>
              <Form.Control
                {...register("password")}
                type="password"
                placeholder="Password"
              />
              <Form.Text className="text_color">
                {errors.password?.message}
              </Form.Text>
            </Form.Group>
            <div className="d-grid gap-2">
              <Button variant="primary" type="submit">
                SignUp
              </Button>
            </div>
          </Form>
        </Row>
      </Container>
    </>
  );
}
