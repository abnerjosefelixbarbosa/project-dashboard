import { BASE_URL } from "../utils/request";

type DataSendEmail = {
    email: string
}

export async function sendEmail(data: DataSendEmail) {
    const res = await fetch(`${BASE_URL}/api/emails/send`, {
        method: "POST",
        headers: {
          "content-type": "application/json",
        },
        body: JSON.stringify(data),
    });
}