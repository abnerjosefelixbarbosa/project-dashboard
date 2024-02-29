import { Button, Container, Form, Row } from "react-bootstrap";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/ReactToastify.css";

const regex = RegExp("^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$");
const date = new Date();
//date.setDate(-1);
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
    .max(date, "date birth user past"),
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

  async function handleCreate(data: FormCreateAccount) {
    try {
      if (validateForm(data)) {
        console.log("ok")
      }
    } catch (e: any) {
      toast.error(e.message);
    }
  }

  function validateForm(data: FormCreateAccount) {
    const date1 = `${date.getFullYear()}-${date.getMonth()}-${date.getDate()}`;
    const date2 = `${data.dateBirthUser.getFullYear()}-${data.dateBirthUser.getMonth()}-${data.dateBirthUser.getDate() + 1}`;
    if (date1 === date2) {
      setError("dateBirthUser", { message: "date birth user past" })
      return false;
    }
    return true;
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
