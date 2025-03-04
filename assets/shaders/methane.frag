#define HIGHP

#define NSCALE 300.0 / 2.0

uniform sampler2D u_texture;
uniform sampler2D u_noise;

uniform vec2 u_campos;
uniform vec2 u_resolution;
uniform float u_time;

varying vec2 v_texCoords;

const float mscl = 30.0;
const float mth = 5.0;

void main(){
    vec2 c = v_texCoords;
    vec2 v = vec2(1.0/u_resolution.x, 1.0/u_resolution.y);
    vec2 coords = vec2(c.x / v.x + u_campos.x, c.y / v.y + u_campos.y);

    float stime = u_time / 5.0;

    vec4 sampled = texture2D(u_texture, c + vec2(sin(stime/3.0 + coords.y/0.75) * v.x, 0.0));
    vec4 color = vec4(sampled.rgb, 1.0) * vec4(0.9, 0.9, 1, 1.0);

    float tester = mod((coords.x + coords.y*0.8 + sin(stime / 8.0 + coords.x/5.0 - coords.y/100.0)*2.0) +
    sin(stime / 20.0 + coords.y/3.0) * 1.0 +
    sin(stime / 10.0 - coords.y/2.0) * 2.0 +
    sin(stime / 7.0 + coords.y/1.0) * 0.5 +
    sin(coords.x / 3.0 + coords.y / 2.0) +
    sin(stime / 20.0 + coords.x/4.0) * 1.0, mscl);

    if(tester < mth){
        color *= 1.125;
    }

    //methane code (modified cryofluid)
    c = v_texCoords.xy;
    coords = vec2(c.x * u_resolution.x + u_campos.x, c.y * u_resolution.y + u_campos.y);

    float btime = u_time / 5000.0;
    //change the first two numbers to adjust wave thickness
    float wave = abs(sin(coords.x * 0.5 + (coords.y * 0.4)) + 0.1 * sin(2.5 * coords.x) + 0.15 * sin(3.0 * coords.y)) / 30.0;
    float noise = wave + (texture2D(u_noise, (coords) / NSCALE + vec2(btime) * vec2(-0.2, 0.8)).r + texture2D(u_noise, (coords) / NSCALE + vec2(btime * 1.1) * vec2(0.8, -1.0)).r) / 2.0;
    //vec4 color = texture2D(u_texture, c);

    if(noise > 0.54 && noise < 0.57){
        color.rgb *= vec3(1.1, 1.15, 1.125);
    }else if (noise > 0.49 && noise < 0.62){
        color.rgb *= vec3(1.05, 1.1, 1.075);
    }

    gl_FragColor = vec4(color.rgb, min(sampled.a * 100.0, 1.0));
}