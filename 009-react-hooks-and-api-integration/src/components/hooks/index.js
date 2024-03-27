import ClickMeUsingClass from './ClickMeUsingClass';
import ClickMeUsingHooks from './ClickMeUsingHooks';

function ClickMe() {
    return (
        <div style={{textAlign: 'center', marginTop: '2em'}}>
            <h3>ClickMeUsingClass</h3>
            <ClickMeUsingClass />
            <h3>ClickMeUsingHooks</h3>
            <ClickMeUsingHooks />
        </div>
        );
}

export default ClickMe;
