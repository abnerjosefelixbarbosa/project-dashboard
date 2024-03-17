import { zodResolver } from "@hookform/resolvers/zod";
import { Button, Container, Form, Row } from "react-bootstrap";
import { useForm } from "react-hook-form";
import { ToastContainer, toast } from "react-toastify";
import { z } from "zod";
import { updatePasswordAccount } from "../../../service/serviceAccount";
import { useSearchParams } from "react-router-dom";

const regex = RegExp("^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$");
const schema = z.object({
  password: z
    .string()
    .nonempty("Password empty")
    .regex(regex, "Password invalid")
    .max(20, "Passowrd max 20"),
});

type FormUpdate = z.infer<typeof schema>;

export function FormUpdate() {
  const {
    setError,
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormUpdate>({
    mode: "all",
    reValidateMode: "onSubmit",
    resolver: zodResolver(schema),
  });
  //const [param] = useSearchParams();
  //const email = param.get("email")!;
  const email = "email1@gmail.com";

  async function handUpdate(data: FormUpdate) {
    try {
      //console.log(email)
      const obj = {
        passwordUser: data.password,
      };
      await updatePasswordAccount(email, obj);
      toast.success("Ok");
    } catch (e: any) {
      const message = `${e.message}`;
      if (message.includes("Password user exist")) {
        setError("password", { message: message });
      }
    }
  }

  return (
    <>
      <ToastContainer />
      <Container className="container_form">
        <Row>
          <Form onSubmit={handleSubmit(handUpdate)}>
            <Form.Group className="mb-3">
              <Form.Label>Password:</Form.Label>
              <Form.Control {...register("password")} type="password" />
              <Form.Text className="text_color">
                {errors.password?.message}
              </Form.Text>
            </Form.Group>
            <div className="d-grid gap-2">
              <Button variant="primary" type="submit" size="lg">
                Update
              </Button>
            </div>
          </Form>
        </Row>
      </Container>
    </>
  );
}
