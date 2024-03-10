import { Button, Container, Form, Row } from "react-bootstrap";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/ReactToastify.css";
import { useNavigate, useSearchParams } from "react-router-dom";
import { createProject } from "../../../service/serviceProject";
import { ValidationFormError } from "../../../exception/validationFormError";

const date = new Date();
const schema = z.object({
  name: z.string()
    .nonempty("Name empty")
    .max(100, "Name max 100"),
  description: z
    .string()
    .nonempty("Description empty")
    .max(200, "Description max 200"),
  dateStart: z.coerce.date()
    .min(date, "Date start future"),
  dateEnd: z.coerce.date(),
  budget: z.coerce.number(),
});

type FormCreate = z.infer<typeof schema>;

export function FromCreate() {
  const {
    register,
    handleSubmit,
    setError,
    formState: { errors },
  } = useForm<FormCreate>({
    mode: "all",
    reValidateMode: "onSubmit",
    resolver: zodResolver(schema),
  });
  const navigate = useNavigate();
  const [params] = useSearchParams();
  const token = params.get('token')!;
  const accountId = params.get('accountId')!;

  async function handleCreate(data: FormCreate) {
    try {
      data.dateStart.setDate(data.dateStart.getDate() + 1);
      data.dateEnd.setDate(data.dateEnd.getDate() + 1);
      validateForm(data);
      const obj = {
        accountId: accountId!,
        ...data
      }
      const res = await createProject(obj, token);
      if (res.id) {
        navigate(`/project/list?token=${token}&accountId=${accountId}`, {
          replace: true,
        });
      }
    } catch (e: any) {
      const message = `${e.message}`;
      if (message.includes("Date start future")) {
        setError("dateStart", { message: message });
      } else if (message.includes("Date end equal date start")) {
        setError("dateEnd", { message: message });
      } else if (message.includes("Date end before date start")) {
        setError("dateEnd", { message: e.message });
      } else if (message.includes("Budget equal 0.00")) {
        setError("budget", { message: message });
      } else {
        toast.error(message);
      }
    }
  }

  function validateForm(data: FormCreate) {
    if (data.dateStart.toDateString() == date.toDateString()) {
      throw new ValidationFormError("Date start future");
    }
    if (data.dateEnd.toDateString() == data.dateStart.toDateString()) {
      throw new ValidationFormError("Date end equal date start");
    }
    if (data.dateEnd.getTime() < data.dateStart.getTime()) {
      throw new ValidationFormError("Date end before date start");
    }
    if (data.budget == 0) {
      throw new ValidationFormError("Budget equal 0.00");
    }
  }

  return (
    <>
      <ToastContainer />
      <Container className="container_project_save_form">
        <Row>
          <Form onSubmit={handleSubmit(handleCreate)}>
            <Form.Group className="mb-3">
              <Form.Label>Name:</Form.Label>
              <Form.Control {...register("name")} type="text" />
              <Form.Text className="text_color">
                {errors.name?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Description:</Form.Label>
              <Form.Control
                {...register("description")}
                as="textarea"
                style={{ height: "100px" }}
              />
              <Form.Text className="text_color">
                {errors.description?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Date start:</Form.Label>
              <Form.Control {...register("dateStart")} type="date" />
              <Form.Text className="text_color">
                {errors.dateStart?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Date end:</Form.Label>
              <Form.Control {...register("dateEnd")} type="date" />
              <Form.Text className="text_color">
                {errors.dateEnd?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Budget:</Form.Label>
              <Form.Control
                {...register("budget")}
                type="text"
                onChange={(e) => {
                  let value = e.target.value;
                  value = value.replace(/(\D)/g, "");
                  value = value.replace(/(\d+)(\d{2})$/, "$1.$2");
                  e.target.value = value;
                }}
              />
              <Form.Text className="text_color">
                {errors.budget?.message}
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
