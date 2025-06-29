import { useNavigate } from "react-router-dom";

export const Unauthorized = () => {

    const navigate = useNavigate();

    const goBack = () => navigate(-1);

  return (
     <section>
         <h1>Unauthorized</h1>
         <br />
         <p>you do not have access to the requested page.</p>
         <div className="flexGrow">
            <button onClick={goBack}>Go back</button>
         </div>
     </section>
    )
}