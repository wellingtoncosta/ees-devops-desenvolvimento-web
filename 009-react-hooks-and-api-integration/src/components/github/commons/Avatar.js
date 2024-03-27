export default function Avatar({url, size}) {
    return (
        <div className='p-2'>
            <img
                style={{borderRadius: '50%', width: `${size ? size : 4}em`}}
                alt='Avatar'
                src={url}
            />
        </div>
    )
}