import { Button, Container, Form, Row } from "react-bootstrap";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { loginAccount } from "../../../service/serviceAccount";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/ReactToastify.css";
import { Link, useNavigate } from "react-router-dom";

const regex = RegExp("^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$");
const schema = z.object({
  emailUser: z.string()
    .nonempty("Email empty")
    .email("Email invalid"),
  passwordUser: z.string()
    .nonempty("Password empty")
    .regex(regex, "Password invalid")
    .max(20, "Passowrd max 20"),
});

type FormLogin = z.infer<typeof schema>;

export function FormLogin() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormLogin>({
    mode: "all",
    reValidateMode: "onSubmit",
    resolver: zodResolver(schema),
  });
  const navigate = useNavigate();

  async function handleLogin(data: FormLogin) {
    try {
      const res = await loginAccount(data);
      navigate(`/project/list?token=${res.token}&accountId=${res.id}`, {
        replace: true
      });
    } catch (e: any) {
      const message = `${e.message}`;
      toast.error(message);
    }
  }

  return (
    <>
      <ToastContainer />
      <Container className="container_form">
        <Row>
          <Form onSubmit={handleSubmit(handleLogin)}>
            <Form.Group className="mb-3">
              <Form.Label>Email:</Form.Label>
              <Form.Control {...register("emailUser")} type="email" />
              <Form.Text className="text_color">
                {errors.emailUser?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Password:</Form.Label>
              <Form.Control {...register("passwordUser")} type="password" />
              <Form.Text className="text_color">
                {errors.passwordUser?.message}
              </Form.Text>
            </Form.Group>
            <div className="d-grid gap-2">
              <Button variant="primary" type="submit" size="lg">
                Login
              </Button>
              ou
              <Link to={"/account/create"}>
                create account
              </Link>
            </div>
          </Form>
        </Row>
      </Container>
    </>
  );
}
