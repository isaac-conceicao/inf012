import sad from '../../assets/sad.png'
import './notfound.css';

export default function NotFound() {
    return (
        <div className='notfound-container'>
            <div className='notfound-card'>
                <h1 className='notfound-title'>Erro 404</h1>
                <h2 className='notfound-subtitle'>Página não encontrada!</h2>
                <img className='notfound-image' src={sad}/>
            </div>
        </div>
    );
}