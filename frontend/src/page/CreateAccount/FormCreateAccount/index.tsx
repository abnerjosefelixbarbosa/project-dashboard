import { Button, Container, Form, Row } from "react-bootstrap";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { ValidationFormError } from "../../../exception/validationFormError";
import { createAccount } from "../../../service/serviceAccount";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/ReactToastify.css";

const regex = RegExp("^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$");
const date = new Date();
const schema = z.object({
  nameUser: z
    .string()
    .nonempty("Name user empty")
    .max(100, "max 100"),
  emailUser: z
    .string()
    .nonempty("Email user empty")
    .email("Email user invalid"),
  passwordUser: z
    .string()
    .nonempty("Password user empty")
    .regex(regex, "Password user invalid")
    .max(20, "Passowrd user max 20"),
  dateBirthUser: z
    .coerce
    .date()
    .max(date, "Date birth user past"),
});

type FormCreateAccount = z.infer<typeof schema>;

export function FormCreateAccount() {
  const {
    register,
    handleSubmit,
    setError,
    formState: { errors },
  } = useForm<FormCreateAccount>({
    mode: "all",
    reValidateMode: "onSubmit",
    resolver: zodResolver(schema),
  });
  const navigate = useNavigate();

  async function handleCreate(data: FormCreateAccount) {
    try {
      data.dateBirthUser.setDate(data.dateBirthUser.getDate() + 1);
      validateForm(data);
      const res = await createAccount(data);
      if (res.id !== undefined) {
        navigate("/account/login", {
          replace: true
        });
      }
    } catch (e: any) {
      const message = `${e.message}`; 
      if (message.includes("Date birth user")) {
        setError("dateBirthUser", { message: message });
      } else {
        toast.error(e.message);
      }
    }
  }

  function validateForm(data: FormCreateAccount) {
    if (data.dateBirthUser.toDateString() == date.toDateString()) {
      throw new ValidationFormError("Date birth user past");
    }
  }

  return (
    <>
      <ToastContainer />
      <Container className="container_form">
        <Row>
          <Form onSubmit={handleSubmit(handleCreate)}>
            <Form.Group className="mb-3">
              <Form.Label>Name user:</Form.Label>
              <Form.Control type="text" {...register("nameUser")} />
              <Form.Text className="text_color">
                {errors.nameUser?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Email user:</Form.Label>
              <Form.Control type="email" {...register("emailUser")} />
              <Form.Text className="text_color">
                {errors.emailUser?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Password user:</Form.Label>
              <Form.Control type="password" {...register("passwordUser")}/>
              <Form.Text className="text_color">
                {errors.passwordUser?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Date birth user:</Form.Label>
              <Form.Control type="date" {...register("dateBirthUser")}/>
              <Form.Text className="text_color">
                {errors.dateBirthUser?.message}
              </Form.Text>
            </Form.Group>
            <div className="d-grid gap-2">
              <Button variant="primary" type="submit" size="lg">
                Create
              </Button>
            </div>
          </Form>
        </Row>
      </Container>
    </>
  );
}
