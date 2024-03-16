import { zodResolver } from "@hookform/resolvers/zod";
import { Button, Container, Form, Row } from "react-bootstrap";
import { useForm } from "react-hook-form";
import { ToastContainer, toast } from "react-toastify";
import { z } from "zod";
import { sendEmail } from "../../../service/serviceEmail";

const schema = z.object({
  email: z.string()
    .nonempty("Email empty")
    .email("Email invalid"),
});

type FormLogin = z.infer<typeof schema>;

export function FormSend() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormLogin>({
    mode: "all",
    reValidateMode: "onSubmit",
    resolver: zodResolver(schema),
  });

  async function handleSend(data: FormLogin) {
    try {
        await sendEmail(data);
        toast.success("Uma menssagem foi enviada para seu email");
    } catch (e:any) {
        const message = `${e.message}`;
        toast.error(message);
    }
  }

  return (
    <>
      <ToastContainer />
      <Container className="container_form">
        <Row>
          <Form onSubmit={handleSubmit(handleSend)}>
            <Form.Group className="mb-3">
              <Form.Label>Email:</Form.Label>
              <Form.Control type="email" {...register("email")} />
              <Form.Text className="text_color">
                {errors.email?.message}
              </Form.Text>
            </Form.Group>
            <div className="d-grid gap-2">
                <Button variant="primary" type="submit" size="lg">
                    Send
                </Button>
            </div>
          </Form>
        </Row>
      </Container>
    </>
  );
}
